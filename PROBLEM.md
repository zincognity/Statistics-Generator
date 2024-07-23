# Generador de Estadísticas

## Description:

La plataforma de datos abiertos del [Gobierno Del Perú](https://www.datosabiertos.gob.pe/) pone a disposición de la ciudadanía diversas bases de datos de interés público. Estas bases de datos son de libre acceso y se encuentran generalmente en formatos de fácil procesamiento (.xls, .xlsx, .txt, .csv, etc.)
De esta fuente de datos se ha obtenido la base de datos “Catálogo Sísmico del Perú”, el cual contiene información de eventos sísmicos desde el año 1960 al 2021.
Debido a que estos datos no se encuentran procesados se requiere el desarrollo de una aplicación en Java que realice su procesamiento a fin de generar estadísticas útiles que permitan mejorar la forma en que los datos son visualizados.
El archivo que se proporciona está en formato .csv y contiene 22712 registros.

![Catálogo Sísmico Perú](/images/image.png)

Los datos de cada registro son:

- ID: número entero correlativo.
- FECHA_UTC: fecha sin formato (ejemplo: 19600113 → 13/01/1960)
- HORA_UTC: hora sin formato (ejemplo: 154034 → 15:40:34)
- LATITUD: valor decimal, positivo o negativo.
- LONGITUD: valor decimal, positivo o negativo.
- PROFUNDIDAD: numérico entero.
- MAGNITUD: valor decimal.

La aplicación cargará los datos del archivo proporcionado y deberá ofrecer las siguientes opciones de procesamiento estadístico al usuario:

1. Tabla con el número de eventos sísmicos por año dado un rango de años.
2. Tabla con el número de eventos sísmicos por mes dado un año.
3. Tabla con el número de eventos sísmicos por mes dados un rango de magnitudes y un año.
4. Tabla con el número de eventos sísmicos por cada hora dado un año.

Todos los cuadros mostrados al usuario en pantalla serán reportes ASCII debidamente formateados.
Luego de mostrar el reporte solicitado, debe consultarse al usuario si desea exportarlo.
La exportación deberá crear un archivo con los mismos datos mostrados en pantalla, y dispuestos con el mismo formato. Deberá incluirse además en el archivo el título del reporte.

## Ejemplo de reporte ASCII:

Reporte B: Tabla con el número de eventos sísmicos por mes dado un año.
(en este caso los datos corresponden al año 2020)

| Nº    | MES       | FREC | PORC    |
| ----- | --------- | ---- | ------- |
| 01    | ENERO     | 73   | 9.03%   |
| 02    | FEBRERO   | 67   | 8.29%   |
| 03    | MARZO     | 68   | 8.42%   |
| 04    | ABRIL     | 90   | 11.14%  |
| 05    | MAYO      | 99   | 12.25%  |
| 06    | JUNIO     | 49   | 6.06%   |
| 07    | JULIO     | 51   | 6.31%   |
| 08    | AGOSTO    | 60   | 7.43%   |
| 09    | SETIEMBRE | 48   | 5.94%   |
| 10    | OCTUBRE   | 66   | 8.17%   |
| 11    | NOVIEMBRE | 70   | 8.66%   |
| 12    | DICIEMBRE | 67   | 8.29%   |
| ---   | --------- | ---- | ------  |
| TOTAL |           | 808  | 100.00% |

## Acceso al sistema

El usuario debe ingresar sus credenciales para tener acceso a la aplicación. El usuario tiene tres intentos para iniciar sesión. Un intento de sesión fallido debe mostrar un mensaje de error y el número del intento.
Las credenciales (usuarios y contraseñas) serán leídas de un archivo de texto plano “usuarios.txt”

## Menús de opciones

Menú principal: es la pantalla principal desde la cual se puede acceder a los demás módulos.

| MENU PRINCIPAL |
| -------------- |

1. Número de eventos sísmicos por año dado un rango de años.
2. Número de eventos sísmicos por mes dado un año.
3. Número de eventos sísmicos por mes dados un rango de magnitudes y un año
4. Número de eventos sísmicos por cada hora dado un año.
5. FIN DEL PROGRAMA

Ingrese opción [1 – 4]

Adicionalmente, cada módulo debe tener su propio menú de opciones:

| MÓDULO 01 – EVENTOS POR RANGO DE AÑOS |
| ------------------------------------- |

1. Imprimir por pantalla.
2. Exportar a archivo plano.
3. Volver al Menú Principal

Ingrese opción [1-2]

## Archivos de auditoría

Dado que el programa debe controlar excepciones, se deberá incluir funcionalidad para capturar excepciones y registrarlas en el archivo “auditoria.log”.
Por cada excepción debe registrarse: fecha y hora del error, nombre del usuario, tipo de error y mensaje de error.