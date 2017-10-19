### Download and install the `tree` and `wget` command

Please install the ```tree``` command before moving forward:

   - Linux
        ```
        sudo apt-get install tree
        sudo apt-get install wget
        ```

   - MacOSX
       
        - Option 1:
        
          - install tree
          
          http://apple.stackexchange.com/questions/128876/whats-the-equivalent-of-the-unix-tree-command-on-osx
        
          - install wget 
          
          http://stackoverflow.com/questions/4572153/os-x-equivalent-of-linuxs-wget
       
        - Option 2 (Via brew):
            
            `Comment from @Hilmerc as to why we are doing the below: Using MacOS Sierra 10.12.6, but we are not sure in which Mac version the brew command started to be necessary for the process.`
          
            You will need the latest version of brew. If you don't have brew installed, execute the next instruction or go [here](https://brew.sh/) for more information. 
        
            - `ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`
          
          - `brew install tree` 
        
            http://apple.stackexchange.com/questions/128876/whats-the-equivalent-of-the-unix-tree-command-on-osx
        
          - `brew install wget` 
          
            http://stackoverflow.com/questions/4572153/os-x-equivalent-of-linuxs-wget
        
        
   - Windows
        ```
        install `git-bash` (recommended) or `cgywin`
        ```
        
        The `tree` command is built into Windows. From within `git-bash` or `cygwin`.
        In case a script is invoking the `tree` command and failing, please replace the `tree -fl` keywords with `cmd //c "tree /f /a"`. If that does not work either use `ls -R` instead, except you won't see the output in a tree format.
        
        Or manually convert the `.sh` files into `.bat`, with minor tweaks should also work there.

        Thanks Richard Kolb ([@rjdkolb](http://github.com/rjdkolb)) for your continued support in this area.

Now return to the [Download and install tree and wget](./README.md#download-and-install-the-tree-and-wget-command) section in the main [README.md](./README.md) file and continue with the rest of the steps.
