class Pessoa:
    def __init__(self, ano_nascimento):
        self.ano_nascimento = ano_nascimento

ano = input("Dige o Ano de Nascimento: ")
p = Pessoa(ano)

print("A idade é: ", 2025 - p.ano_nascimento)