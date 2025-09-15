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
        return self.x * otro.x + self.y * otro.y + self.z * otro.z

    def __xor__(self, otro):
        return AlgebraVectorial(
            self.y * otro.z - self.z * otro.y,
            self.z * otro.x - self.x * otro.z,
            self.x * otro.y - self.y * otro.x
        )

    def modulo(self):
        return math.sqrt(self.x**2 + self.y**2 + self.z**2)


    def esPerpendicular(self, otro):
        return self * otro == 0

    def esParalelo(self, otro):
        cruz = self ^ otro
        return cruz.x == 0 and cruz.y == 0 and cruz.z == 0

    def proyeccion(self, otro):
        factor = (self * otro) / (otro.modulo()**2)
        return AlgebraVectorial(otro.x * factor, otro.y * factor, otro.z * factor)

    def componente(self, otro):
        return (self * otro) / otro.modulo()

    def __str__(self):
        return f"({self.x}, {self.y}, {self.z})"

if __name__ == "__main__":
    a = AlgebraVectorial(1, 2, 3)
    b = AlgebraVectorial(2, 4, 6)
    c = AlgebraVectorial(-2, 1, 0)

    print("Vector a:", a)
    print("Vector b:", b)
    print("Vector c:", c)

    print("\n¿a ⟂ c?:", a.esPerpendicular(c))
    print("¿a ∥ b?:", a.esParalelo(b))

    print("\nProyección de a sobre b:", a.proyeccion(b))
    print("Componente de a en b:", a.componente(b))
