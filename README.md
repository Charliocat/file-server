# File Storage Service


## Requirements
- Java 8+ : [JDK 8]
- Maven 3.6.0+ : [Maven]
- Node v14.5.0 : [Node]

##Building and Running Application
- Build with Maven wrapper:
```
./mvnw clean package
```

- Run Application jar with:
```
java -jar application/target/application-1.0.0.jar
```

The fullstack application runs on port: 8091.
<br>
The Frontend and CLI communicate using http://localhost:8091 (hardcoded) it can be changed in the main method
of ApplicationCLI and package.json from frontend.
<br>
When running the application a folder named storage-temp is created to the root directory
and contains the uploaded files.

- Run CLI-Application jar with:
```
java -jar cli/target/cli-1.0.0.jar 
```
#### Commands

- To list files:
`list-files`
```
file1.txt
file2.ppt
```

- To upload a file:
`upload-file`
```
Usage:  upload-file [-h] -f=FILE_PATH
upload file
  -f, --file=FILE_PATH   file path
  -h, --help
```

- To delete a file:
`delete`
```
Usage:  delete [-h] -f=FILE_NAME
delete file
  -f, --file=FILE_NAME   file name to delete
  -h, --help
```
- To exit CLI:
`exit`

All the available commands:
```
fs-store> help
  System:
    exit        exit from app/script
    help        
  Builtins:
    history     list history of commands
    keymap      manipulate keymaps
    less        file pager
    nano        edit files
    setopt      set options
    setvar      set lineReader variable value
    ttop        display and update sorted information about threads
    unsetopt    unset options
    widget      manipulate widgets
  PicocliCommands:
    delete      delete file
    help        
    list-files  list files
    upload-file upload file
````

The cli communicates to the backend through http


### Application Design
The application consists of 3 java modules and, a React frontend
- `application`: core module contains the business logic and the rest controller
- `rest-client:` contains an http client to communicate with the application
- `cli`: command line interface
- `frontend-cg-file-server`: ReactJS application

### Logs
Each run a new logs/file-server-logs.log file is created with the last execution output.

[JDK 8]: https://jdk.java.net/8/
[Maven]: https://maven.apache.org/install.html
[Node]: https://nodejs.org/en/blog/release/v14.5.0/

