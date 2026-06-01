class Calculadora {
  somar(a, b) {
    return a + b;
  }
}

const calc = new Calculadora();

console.log(calc.somar(2, 3));      // ✅ 5  -> funciona como esperado
console.log(calc.somar("2", 3));    // ⚠️ "23" -> erro lógico!
console.log(calc.somar(true, 2));   // ⚠️ 3   -> true vira 1
console.log(calc.somar(null, 2));   // ⚠️ 2   -> null vira 0
console.log(calc.somar(undefined, 2)); // ⚠️ NaN -> undefined não vira número válido
