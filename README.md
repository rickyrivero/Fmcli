
# Fmcli (FileManagementCLI)

Este es el proyecto final del bootcamp de Código Facilito, donde desarrollé una herramienta CLI que permite un manejo de archivos en local. El proyecto esta orientado a que se tenga un manejo más dinámico y sencillo de los archivos mientas uno esta trabajando en algún proyecto o simplemente en el día a día. Requisitos para correr el programa, tener jdk-21 y gradle instalados con sus variables de ambiente correspondientes.

## Intenciones del programa

El programa sirve para listar, crear, obtener información, leer y escribir archivos de una forma más intuitiva para el usuario, con una interfaz explicativa con funciones le dejen saber al usuario el estado de su petición, es decir, si esta fue exitosa o si tuvo algún error y por qué.

Formatos soportados por la función para convertir archivos a pdf: txt, csv, png, jpeg y jpg

## Version

1.2.0

## Tecnologías usadas

- Java
- Gradle (Kotlin)
- Picocli
- JUnit
- Git

## Uso

Para sacarle el máximo provecho a esta herramienta es recomendable añadirla a una variable de entorno de sus sistema operativo. Para eso he creado una carpeta "path" en la cual se encuenta el bin y las librerias del proyecto, es necesario que copie la dirreción de la carpeta bin y añada como variable, en esta carpeta se encuenta un archivo .bat para ejecutar los comandos en la terminal sin necesidad de estar en la carpeta del programa.

### Modo de uso:

#### Obtener ayuda:
```
  fmcli -h
```

#### Crear un archivo:
```
  fmcli -c -rd=`directorio deseado` -fn=`nombre del archivo`
```

#### Convertir archivo a pdf:
```
  fmcli --pdf -rd=`directorio deseado` -fn=`nombre del archivo` -on=`nombre del pdf`
```

### Ejecución

El usuario debe seleccionar el comando que desea ejecutar, luego debe de pasarle al programa el directorio y el nombre o extensión del archivo (exceptuando el comando `-l, --list` que solo se le pasa el directorio ya que este imprime de forma organizada lo que se haya encontrado en la ruta).

Una vez ejecutada la función esta devuelve un mensaje en texto con el estatus de su petición, el resultado de la función y un 0 si fue exitosa la ejecución o 1/-1 de haber un error, aunque este último paso no es tan importante ya que se agregaron mensajes de texto para que el usuario tenga entendimiento de qué fue lo que pasó.

Cada comando cuenta con un valor que se le debe proporcionar para poder llevar un correcto funcionamiento su ejecución, estos valores véase por ejemplo `-rd` o `-fn` pueden ser llamados en cualquier orden, no tienen un índice fijo siempre y cuando los valores sean correctos; el único valor que si debe seguir un lineamiento es `-co` ya que este es el valor del contenido que va a ser agregado a nuestro archivo y se debe escribir así `-co="Esta es la forma de pasarle el contenido a la variable"`.

## Opciones

```
  -c, --create      Create a new file
      -co, --content=<content>
                    File content
  -e, --extension   Search for files matching the extension in the current
                      directory
      -fn, --fileName=<fileName>
                    The name of the file to execute
      -ge, --getExtension=<extension>
                    The extension of the files to list
  -h, --help        Show this help message and exit.
  -i, --info        Display file information
  -l, --list        List all the files in current directory
      -on, --outputName=<outputName>
                    Output pdf file name
      --pdf         Transform your file into a PDF
  -r, --read        Read your files
      -rd, --rootDirectory=<rootDirectory>
                    The directory where the command will be executed
  -V, --version     Print version information and exit.
  -w, --write       Write your files

```
## Funcionamiento

Cada clase tiene su Sealed class correspondiente para el manejo más organizado de los outputs, cada Sealed class cuenta a su vez con un máximo de 3 clases que determinan los casos más probables de la ejecución, de esta manera resulta más fácil manejar los errores de ser dados.

También cada función cuenta con sus test unitarios correspondientes poniendo a prueba cada respuesta de la Sealed class más algún otro caso en particular que se pueda dar al ejecutar la función.

### Estructura

#### Create:

- FileCreation
	- createFile
- SearchCreateResult
	- NoFileCreated
    - FileAlreadyExists
    - FileCreated

#### Info:

- FileInformation
	- infoFile
- SearchInfoResult
	- InfoSuccess
    - InfoError

#### List:

- FileListing
	- getFilesByExtension
	- getFilesAndDirectories
- SearchFileResult
	- NoFilesFound
    - DirectoryNotFound
    - FilesFound

#### PDF:

- FilePdf
    - transformToPdf
    - getFileExtension
- PdfFileResult
    - PdfFileError
    - PdfFileResult
    - PdfFileSuccess

#### Reading

- FileReading
    - readFile
- SearchReadResult
    - FileReadError
    - FileReadSuccess

#### Writing:

- FileWriting
    - writeFile
- SearchWriteResult
    - FileWriteError
    - FileWritten

