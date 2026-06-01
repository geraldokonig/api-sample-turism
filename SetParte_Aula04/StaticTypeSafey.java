public class StaticTypeSafey {
    public static void main(String[] args) {
        double x = 10;
        //double y = "20";   // aqui já começa os erros em tempo de compilação
        String y = "20"; // se eu tenho o valor que é String, a variável tem que ser String
        System.out.println("Soma: " + soma(x, y)); // ERROS: Não tem como vc errar
        System.out.println("Soma: " + divide(x, y));
    }
    private static double soma(double a, double b){
        return a + b;
    }
    private static double divide(double a, double b){
        return a / b;
    }
}

