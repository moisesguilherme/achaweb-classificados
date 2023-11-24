### Versão da GraalVM
```sh
java --version
openjdk 21 2023-09-19
OpenJDK Runtime Environment GraalVM CE 21+35.1 (build 21+35-jvmci-23.1-b15)
OpenJDK 64-Bit Server VM GraalVM CE 21+35.1 (build 21+35-jvmci-23.1-b15, mixed mode, sharing)
```
## Usando o SDKMan para instalar a GraalVM
A forma mais fácil de instalar o JDK GraalVM foi usando SDK Man
```sh
sdk upgrade
sdk list java
sdk install java 21-graalce
sdk use java 21-graalce
```

### Dependência SO (Linux - Ubuntu)
Certifique que possui a bibliteca (build-essential) no seu Sistema Operacional.
```sh
sudo apt-get install build-essential
```
#### Para rodar o projeto:
```sh
./gradlew bootRun
```

#### Para compliar o binário com a GraalVM
Obs.: Esse processo pode levar alguns minutos e consome cerca de 8GB de RAM.
```sh
./gradlew nativeCompile
```

#### Para rodar o binário

O binário gerado fica na pasta build/native/nativeCompile/
```sh
cd build/native/nativeCompile/
./achaweb
```
