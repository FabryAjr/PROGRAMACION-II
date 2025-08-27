class EcuacionLineal:
    def __init__(self, a, b, c, d, e, f):
        self.a = a
        self.b = b
        self.c = c
        self.d = d
        self.e = e
        self.f = f

    def tiene_solucion(self):
        return (self.a * self.d - self.b * self.c) != 0

    def get_x(self):
        return (self.e * self.d - self.b * self.f) / (self.a * self.d - self.b * self.c)

    def get_y(self):
        return (self.a * self.f - self.e * self.c) / (self.a * self.d - self.b * self.c)


if __name__ == "__main__":
    valores = list(map(float, input("Ingrese a, b, c, d, e, f: ").split()))
    eq = EcuacionLineal(*valores)

    if eq.tiene_solucion():
        print(f"x = {eq.get_x()}, y = {eq.get_y()}")
    else:
        print("La ecuación no tiene solución")
