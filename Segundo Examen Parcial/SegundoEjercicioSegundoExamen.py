#Ejercicio 2 Examen Parcial
# Clase Persona
class Persona:
    def __init__(self, nombre, edad, peso):
        self.nombre = nombre
        self.edad = edad
        self.pesoPersona = float(peso)

    def getTarifa(self):
        if self.edad <= 25 or self.edad >= 60:
            return 1.5
        return 3

class Cabina:
    def __init__(self, nroCabina):
        self.nroCabina = nroCabina
        self.personasAbordo = []

    def agregarPersona(self, p):
        if len(self.personasAbordo) >= 10:
            return False

        pesoActual = 0
        for x in self.personasAbordo:
            pesoActual += x.pesoPersona

        if pesoActual + p.pesoPersona > 850:
            return False

        self.personasAbordo.append(p)
        return True

# Metodo adicional Ingresos Totales
    def totalIngresos(self):
        total = 0
        for p in self.personasAbordo:
            total += p.getTarifa()
        return total

    def ingresosRegulares(self):
        total = 0
        for p in self.personasAbordo:
            if p.getTarifa() == 3:
                total += 3
        return total

# Clase Linea
class Linea:
    def __init__(self, color):
        self.color = color
        self.filaPersonas = []      
        self.cabinas = []           
        self.cantidadCabinas = 0

    def agregarPersona(self, p):
        self.filaPersonas.append(p)

    def agregarCabina(self, nroCab):
        c = Cabina(nroCab)
        self.cabinas.append(c)
        self.cantidadCabinas += 1

    def subirPersonaACabina(self, p, nroCab):
        for c in self.cabinas:
            if c.nroCabina == nroCab:
                return c.agregarPersona(p)
        return False

    def verificarCabinas(self):
        for c in self.cabinas:
            peso = sum([x.pesoPersona for x in c.personasAbordo])
            if len(c.personasAbordo) > 10 or peso > 850:
                return False
        return True

    def ingresosTotales(self):
        total = 0
        for c in self.cabinas:
            total += c.totalIngresos()
        return total

    def ingresosRegulares(self):
        total = 0
        for c in self.cabinas:
            total += c.ingresosRegulares()
        return total


#Clase MiTeleferico
class MiTeleferico:
    def __init__(self):
        self.lineas = []        
        self.cantidadingresos = 0.0

    def agregarPersonaFila(self, p, linea):
        for l in self.lineas:
            if l.color == linea:
                l.agregarPersona(p)

    def agregarCabina(self, linea):
        for l in self.lineas:
            if l.color == linea:
                nro = l.cantidadCabinas + 1
                l.agregarCabina(nro)

    def verificarTodo(self):
        for l in self.lineas:
            if not l.verificarCabinas():
                return False
        return True

    def calcularIngresos(self):
        total = 0
        for l in self.lineas:
            total += l.ingresosTotales()
        self.cantidadingresos = total
        return total

    def lineaMayorIngresoRegular(self):
        mayor = None
        maxIngreso = -1

        for l in self.lineas:
            ing = l.ingresosRegulares()
            if ing > maxIngreso:
                maxIngreso = ing
                mayor = l

        return mayor


# Ejemplo o Main

if __name__ == "__main__":
    sistema = MiTeleferico()

    lineaA = Linea("Amarillo")
    lineaR = Linea("Rojo")
    lineaV = Linea("Verde")

    sistema.lineas.append(lineaA)
    sistema.lineas.append(lineaR)
    sistema.lineas.append(lineaV)

    for linea in sistema.lineas:
        sistema.agregarCabina(linea.color)
        sistema.agregarCabina(linea.color)
        sistema.agregarCabina(linea.color)

    p1 = Persona("Angel", 20, 55)
    p2 = Persona("Jorge", 30, 80)
    p3 = Persona("Marco", 65, 70)
    p4 = Persona("Anabel", 50, 60)
    p5 = Persona("Sofia", 10, 40)
    p6 = Persona("Joaquin", 10, 40)

    lineaA.subirPersonaACabina(p1, 1)
    lineaA.subirPersonaACabina(p2, 1)
    lineaA.subirPersonaACabina(p3, 2)

    lineaR.subirPersonaACabina(p4, 1)
    lineaR.subirPersonaACabina(p5, 1)

    lineaV.subirPersonaACabina(p1, 3)
    lineaV.subirPersonaACabina(p3, 3)

    print("Todas las cabinas cumplen reglas?:", sistema.verificarTodo())

    print("Ingreso total del dia de Mi Teleferico:", sistema.calcularIngresos(), "Bs")

    mayor = sistema.lineaMayorIngresoRegular()
    print("La linea con mayor ingreso regular es la linea ", mayor.color)
