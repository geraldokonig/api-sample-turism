package br.edu.atitus.apisample.services;

import br.edu.atitus.apisample.entities.User;
import br.edu.atitus.apisample.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    // Regex para e-mail: exige texto + @ + texto + . + texto (Garante dois ou mais domínios pós-@)
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    // Expressão Regular para conferir se a senha tem pelo menos 1 maiúscula, 1 minúscula e 1 número
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public User save(User newUser) throws Exception {
        if (newUser == null)
            throw new Exception("Objeto Nulo!");

        if (newUser.getName() == null || newUser.getName().isBlank())
            throw new Exception("Nome informado inválido!");
        newUser.setName(newUser.getName().trim());

        if (newUser.getEmail() == null || newUser.getEmail().isBlank())
            throw new Exception("E-mail informado inválido!");
        newUser.setEmail(newUser.getEmail().trim().toLowerCase());

        // VALIDAÇÃO DO FORMATO DE E-MAIL VIA REGEX (Atendendo ao critério obrigatório)
        if (!EMAIL_PATTERN.matcher(newUser.getEmail()).matches()) {
            throw new Exception("E-mail inválido! Deve conter '@' e dois ou mais domínios (ex: gmail.com ou bol.com.br).");
        }

        if (repository.existsByEmail(newUser.getEmail()))
            throw new Exception("Já existe usuário cadastrado com este e-mail!");

        if (newUser.getPassword() == null || newUser.getPassword().length() < 8)
            throw new Exception("Password informado inválido! Deve conter no mínimo 8 caracteres.");

        // VALIDAÇÃO DE QUALIDADE DE SENHA (Pelo menos uma maiúscula, uma minúscula e um número)
        if (!PASSWORD_PATTERN.matcher(newUser.getPassword()).matches()) {
            throw new Exception("Senha insegura! Deve conter pelo menos uma letra maiúscula, uma minúscula e um número.");
        }

        newUser.setPassword(encoder.encode(newUser.getPassword()));

        if (newUser.getType() == null)
            throw new Exception("Tipo de usuário informado inválido!");

        return repository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com este e-mail!"));
    }
}