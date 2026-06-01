export class CalculadoraTs {
  somar(a: number, b: number): number {
    return a + b;
  }
}

const calc = new CalculadoraTs();

console.log(calc.somar(2, 3));   // ✅ ok
//console.log(calc.somar("2", 3)); // ❌ erro de compilação
