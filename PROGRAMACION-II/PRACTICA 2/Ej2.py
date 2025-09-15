import math

class AlgebraVectorial:
    def __init__(self, x=0, y=0, z=0):
        self.x = x
        self.y = y
        self.z = z

    def __add__(self, otro):
        return AlgebraVectorial(self.x + otro.x, self.y + otro.y, self.z + otro.z)

    def __sub__(self, otro):
        return AlgebraVectorial(self.x - otro.x, self.y - otro.y, self.z - otro.z)

    def __mul__(self, otro):
        if isinstance(otro, (int, float)):
            return AlgebraVectorial(self.x * otro, self.y * otro, self.z * otro)
        elif isinstance(otro, AlgebraVectorial):
            return self.x * otro.x + self.y * otro.y + self.z * otro.z
        else:
            raise TypeError("Operación no soportada")

    def __rmul__(self, esc):
        return self.__mul__(esc)

    def __xor__(self, otro):
        return AlgebraVectorial(
            self.y * otro.z - self.z * otro.y,
            self.z * otro.x - self.x * otro.z,
            self.x * otro.y - self.y * otro.x
        )

    def __str__(self):
        return f"({self.x}, {self.y}, {self.z})"

    def modulo(self):
        return math.sqrt(self.x**2 + self.y**2 + self.z**2)

    def normal(self):
        mod = self.modulo()
        if mod == 0:
            raise ValueError("No se puede normalizar el vector nulo")
        return AlgebraVectorial(self.x/mod, self.y/mod, self.z/mod)

    def esPerpendicular(self, otro):
        return self * otro == 0

    def esParalelo(self, otro):
        cruz = self ^ otro
        return cruz.x == 0 and cruz.y == 0 and cruz.z == 0

    def proyeccion(self, otro):
        factor = (self * otro) / (otro.modulo()**2)
        return otro * factor

    def componente(self, otro):
        return (self * otro) / otro.modulo()


if __name__ == "__main__":
    a = AlgebraVectorial(3, 4, 0)
    b = AlgebraVectorial(4, -3, 0)
    c = AlgebraVectorial(6, 8, 0)

    print("Vector a:", a)
    print("Vector b:", b)
    print("Vector c:", c)

    print("\nSuma a+b:", a + b)
    print("Escalar 2*a:", 2 * a)
    print("Longitud de a:", a.modulo())
    print("Normal de a:", a.normal())
    print("Producto escalar a·b:", a * b)
    print("Producto vectorial a×b:", a ^ b)

    print("\n¿a ⟂ b?:", a.esPerpendicular(b))
    print("¿a ∥ c?:", a.esParalelo(c))
    print("Proyección de a sobre c:", a.proyeccion(c))
    print("Componente de a en c:", a.componente(c))
