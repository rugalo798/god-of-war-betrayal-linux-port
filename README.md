<div align="center">

# God of War: Betrayal — Linux Port

**Um experimento de preservação e compatibilidade para executar o jogo Java ME no desktop Linux.**

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-orange?style=for-the-badge)
![Plataforma](https://img.shields.io/badge/plataforma-Linux-1793D1?style=for-the-badge&logo=linux&logoColor=white)
![Java](https://img.shields.io/badge/Java-21%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

*Projeto experimental, feito por etapas — ainda não está totalmente jogável.*

</div>

---

## 🎮 Sobre o projeto

Este projeto busca fazer **God of War: Betrayal**, originalmente lançado para celulares com Java ME, funcionar como uma aplicação desktop no Linux.

A ideia é criar uma camada hospedeira para iniciar o `MIDlet`, adaptar as APIs necessárias e aproveitar o código e os recursos do jogo dentro de uma janela de desktop. É um trabalho de engenharia reversa, compatibilidade e depuração: cada avanço ajuda a revelar mais uma parte do que o jogo espera do ambiente Java ME original.

## 🚧 Progresso do port

O projeto já passou da inicialização básica e chegou a iniciar o carregamento da primeira fase. Ainda há trabalho importante para que a fase execute corretamente.

| Área | Estado atual |
|---|---|
| Inicialização da aplicação desktop | ✅ Funciona |
| Criação da janela Swing | ✅ Funciona |
| Inicialização do `GOWMIDlet` e do canvas principal | ✅ Funciona |
| Execução do loop principal | ✅ Inicia |
| Carregamento inicial | ✅ Passa da tela de loading inicial após uma correção no fluxo |
| Entrada na primeira fase | 🟡 Chega a este ponto, mas congela |
| Jogabilidade completa | ⬜ Ainda não validada |

### 🔧 Em andamento

- Investigar o congelamento que ocorre quando a primeira fase começa.
- Conferir as transições de estado e o processamento dos recursos da fase.
- Validar os acessos a sprites e dados de animação sem mascarar inconsistências.
- Testar a jogabilidade e a estabilidade depois que a fase carregar corretamente.

> **Status atual:** o port abre a janela e atravessa o loading inicial, mas a primeira fase ainda fica congelada. O projeto está em desenvolvimento e não deve ser considerado jogável neste momento.

## 🧰 Tecnologias e estrutura

- **Java** — código e execução do port.
- **Swing/AWT** — janela e integração com o desktop.
- **Camada de compatibilidade Java ME** — adaptações para APIs usadas pelo jogo, incluindo classes `javax.microedition.*`.
- **Shell + `javac`** — compilação e inicialização durante o desenvolvimento.

Arquivos importantes:

```text
gow_port/
├── src/          # Código-fonte Java
├── bin/          # Classes compiladas (geradas localmente)
├── run.sh        # Script de inicialização
└── README.md
```

A estrutura pode evoluir conforme o port amadurecer.

## 🐧 Executar no Linux

### Requisitos

- Linux
- JDK instalado (o desenvolvimento atual foi testado com Java 21)
- Os arquivos de recursos exigidos pelo projeto, mantidos nos caminhos esperados pelo carregador

Confira as ferramentas:

```bash
java -version
javac -version
```

### Compilar

Na raiz do projeto:

```bash
javac -d bin $(find src -name "*.java")
```

### Iniciar

```bash
chmod +x run.sh
./run.sh
```

O script inicia `gow.Main` com `bin` no classpath. Para capturar a saída durante a depuração:

```bash
./run.sh 2>&1 | tee gow_debug.log
```

> Se a compilação ou a execução falhar, confira primeiro se todos os recursos do jogo necessários estão presentes e acessíveis no classpath. A estrutura de recursos ainda faz parte do trabalho de compatibilidade.

## 🗺️ Próximos passos

- [ ] Resolver o congelamento ao entrar na primeira fase.
- [ ] Confirmar que as atualizações e a renderização continuam avançando durante o jogo.
- [ ] Corrigir problemas remanescentes de compatibilidade e carregamento de recursos.
- [ ] Testar controles, áudio, transições e reinicialização.
- [ ] Documentar requisitos e limitações com base em testes reais.
- [ ] Adicionar capturas de tela quando a jogabilidade estiver estável.

## 🤝 Contribuições e relatórios

Relatórios de bugs são úteis. Ao descrever um problema, inclua:

1. A versão do Java (`java -version`).
2. O comando usado para compilar e iniciar.
3. A saída do terminal e, quando relevante, um `jstack` do processo travado.
4. O ponto em que o jogo parou e se o problema pode ser reproduzido.

Evite publicar logs com informações pessoais ou arquivos de jogo que você não tenha autorização para redistribuir.

## ⚖️ Aviso sobre direitos

Este é um projeto independente e não oficial, sem afiliação com Santa Monica Studio ou Sony Interactive Entertainment. **God of War**, seus personagens, marcas, código e recursos pertencem aos respectivos titulares. Este repositório documenta um esforço técnico de compatibilidade; a presença ou redistribuição de código e recursos originais depende dos direitos e permissões aplicáveis. Não distribua arquivos proprietários sem autorização.

---

<div align="center">

**Feito no Linux, um bug de cada vez.** 🐧⚔️

</div>
