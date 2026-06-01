class Calculadora {
    public int somar(int a, int b) {
        return a + b;
    }

    void main() {

        System.out.println(somar(2, 3)); // ✅ 5

        // Os exemplos abaixo NÃO COMPILAM:

        // System.out.println(calc.somar("2", 3));
        // System.out.println(calc.somar(true, 2));
        // System.out.println(calc.somar(null, 2));
        // System.out.println(calc.somar(undefined, 2));

    }
}
