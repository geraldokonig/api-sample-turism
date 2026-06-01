class Calculadora:
    def somar(self, a, b):
        return a + b


calc = Calculadora()

print(calc.somar(2, 3))       # ✅ 5

print(calc.somar("2", 3))     # ❌ TypeError
print(calc.somar(True, 2))    # ⚠️ 3 (True equivale a 1)
print(calc.somar(None, 2))    # ❌ TypeError

# Python não possui um equivalente direto a undefined.
# O mais próximo seria usar uma variável não definida:
# print(calc.somar(undefined, 2))  # ❌ NameError