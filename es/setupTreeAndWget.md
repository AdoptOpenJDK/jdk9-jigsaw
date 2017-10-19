### Descargue e instale los comandos `tree` y `wget`

Por favor instale el comando ```tree``` antes de continuar:

   - Linux
        ```
        sudo apt-get install tree
        sudo apt-get install wget
        ```

   - MacOSX
       
        - Opción 1:
        
          - install tree
          
          http://apple.stackexchange.com/questions/128876/whats-the-equivalent-of-the-unix-tree-command-on-osx
        
          - install wget 
          
          http://stackoverflow.com/questions/4572153/os-x-equivalent-of-linuxs-wget
       
        - Opción 2 (Via brew):
            
            `Comentario de @Hilmerc lo siguiente funcionó en MacOS Sierra 10.12.6, no estamos seguros si en otras versiones el comando brew es necesario para realizar este proceso`
          
            Usted necesitará la última versión de brew. Si no tiene brew instalado, ejecute la siguiente instrucción o clic [aca](https://brew.sh/) para mas información. 
        
            - `ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`
          
          - `brew install tree` 
        
            http://apple.stackexchange.com/questions/128876/whats-the-equivalent-of-the-unix-tree-command-on-osx
        
          - `brew install wget` 
          
            http://stackoverflow.com/questions/4572153/os-x-equivalent-of-linuxs-wget
        
        
   - Windows
        ```
        instale `git-bash` (recommended) o `cgywin`
        ```
        
        El comando `tree` viene con Windows. Desde `git-bash` o `cygwin`.
        En caso que un script que invoque el comando `tree` y falle, por favor reemplace `tree -fl` por `cmd //c "tree /f /a"`. Si esto no funciona puede usar la insruccion `ls -R`, usted no verá la salida en formato de árbol.
        
        O manualmente convierta los archivos `.sh` a `.bat`, con algunos ajustes menores logrará que funcione.

        Gracias Richard Kolb ([@rjdkolb](http://github.com/rjdkolb)) por su constante soporte en esta área.

Ahora regrese a la seccion [Descargue e instale](./README.md#download-and-install-the-tree-and-wget-command) en el documento [README.md](./README.md) y con continúe con el resto de los pasos.
