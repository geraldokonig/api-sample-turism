package br.edu.atitus;
import java.util.ArrayList;
import java.util.List;

import br.edu.atitus.interfaces.Corrida;
import br.edu.atitus.interfaces.Nado;
import br.edu.atitus.interfaces.Voo;

public class Main {
void main() {

    IO.println("Zoo Digital");

    IO.println("Números de Animais no Início: " + Animal.getContador());

    Animal toto = new Cachorro("Totó", 5);
    Animal ariel = new Gato("Ariel", 8);
    Animal flipper = new Golfinho("Flipper", 20);

    List<Animal> animais = new ArrayList<>();
    animais.add(toto);
    animais.add(ariel);
    animais.add(flipper);

    listarAnimais(animais);

    IO.println("Números de Animais no Final: " + Animal.getContador());
}

void listarAnimais(List<Animal> animais) {
    for (Animal animal : animais) {
        IO.println(animal.toString());
        animal.comer(); // De qual classe o método comer() será invocado?
                        // Depende do tipo concreto do objeto
        animal.emitirSom();
        
        if (animal instanceof Corrida) {
            Corrida animalCorredor = (Corrida) animal;
            animalCorredor.correr();
        }
        if (animal instanceof Nado) {
            Nado animalNadador = (Nado) animal;
            animalNadador.nadar();
        }
        if (animal instanceof Voo) {
            Voo animalVoador = (Voo) animal;
            animalVoador.voar();
        }
    }
}





}