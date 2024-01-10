# Calculadora remota simple TCP

 Este proyecto consiste en una implementación de comunicación entre un servidor y un cliente mediante sockets TCP en Java. El servidor realiza operaciones matemáticas básicas y factoriales, acumulando los resultados y enviándolos de vuelta al cliente. El cliente puede enviar operaciones al servidor y recibir el valor acumulado.

## Funcionalidades

- **Servidor TCP (`tcp1ser`):**
  - Escucha las conexiones entrantes en un puerto especificado.
  - Realiza operaciones matemáticas básicas y factoriales.
  - Acumula los resultados de las operaciones.
  - Utiliza el formato TLV (Type-Length-Value) para la comunicación de datos.
  
- **Cliente TCP (`tcp1cli`):**
  - Se conecta a un servidor remoto especificado por dirección IP y puerto.
  - Permite al usuario enviar operaciones matemáticas al servidor.
  - Recibe y muestra el valor acumulado calculado por el servidor.
  - Utiliza el formato TLV para enviar datos al servidor.

## Estructura

El proyecto consta de dos archivos fuente:

- **`tcp1ser.java`:** Contiene la implementación del servidor.
- **`tcp1cli.java`:** Contiene la implementación del cliente.

Ambos archivos comparten funcionalidades relacionadas con la manipulación de datos y la comunicación TCP.

## Instrucciones de Uso

### Servidor (`tcp1ser`)

1. Compile el archivo `tcp1ser.java`.
   
```bash
javac tcp1ser.java
```

2. Ejecute el servidor proporcionando el número de puerto como argumento.
   
```bash
java tcp1ser <puerto>
```

### Cliente (`tcp1cli`)

1. Compile el archivo `tcp1cli.java`.
```bash
javac tcp1cli.java
```

2. Ejecute el cliente proporcionando la dirección IP del servidor y el número de puerto como argumentos.
```bash
java tcp1cli <ip_servidor> <puerto>
```

#### Comandos del Cliente

El cliente acepta operaciones aritméticas en el siguiente formato:

```bash
<operando1> <operador> <operando2>
```
Por ejemplo:

```bash
5 + 3
10 / 2
-7 * 4
5 !
```

Envía "QUIT" para salir del cliente.

## Notas

- El servidor escucha indefinidamente en el puerto especificado.
- El cliente puede enviar operaciones al servidor y recibir el resultado acumulado.
- Se utiliza el formato TLV para la comunicación de datos entre el cliente y el servidor.
