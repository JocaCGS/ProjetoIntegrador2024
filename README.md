  # Projeto Integrador - Daniel Santos e Joaquim
* 22/12/2024 - Joaquim configurou as telas e deu IDs aos atributos das telas (botao,  text, textfield, etc), adicionou a imagem padrao do projeto e adicionou todas as telas com seus respectivos nomes e suas controllers com imagens ja configuradas. Proximo passo sera conectar todas as telas de forma que nao necessariamente interajam com o usuario mas que de para navegar pelo projeto, mesmo que sem poder fazer nada.

* 07/01/2025 - Joaquim e Daniel fizeram o diagrama de banco de dados e montaram o banco de dados phpMyAdmin.

* 08/01/2025 - Joaquim fez a tela de cadastro e a conexao com o banco e Daniel trabalhou algumas coisas no banco de dados. Tela de Cadastro completa e funcional.

* 09/01/2025 <16:00h> - Joaquim terminou a tela de login. A programacao faz uma analise que usa algumas stored procedures que verificam se o usuario existe no banco, se o usuario esta ou nao na tabela de administradores e dependendo do resultado, salva o usuario ou em um Singleton ClienteLogado ou em um Singleton AdministradorLogado e procede com a proxima tela. Caso contrario, emite um erro no console explicando o porque nao foi logado nem avancou para a proxima tela.

* 09/01/2025 <23:57h> - Joaquim deu inicio a tela de Manter Viagem. A programacao adiciona o conteudo escrito nos TextFields no banco de dados e em uma ObservableList, usando um StringProperty. Dessa forma, fica listada na direita da aplicacao em uma TableView todas as viagens, e essa TableView e atualizada automaticamente na tela. Daniel deu inicio a criacao de slides do TCA para apresentacao.

* 10/01/2025 <01:15> - Joaquim adicionou algumas DAOS para uso futuro. Elas agilizam o processo de CRUD das mesmas.

* 10/01/2025 <14:41> - Joaquim terminou a tela de Manter Viagem. A programacao agora permite o administrador a adicionar viagens, e atraves da lista que se atualiza em tempo real, permite o administrador a editar ou deletar os valores de forma pratica sem nenhuma troca de tela, permitindo fazer todas as alteracoes em uma unica tela e com uma lista que se atualiza em tempo real. Todos os dados alterados, adicionados ou removidos sao atualizados automaticamente no banco de dados.

* 15/01/2025 <14:00> - Joaquim deu inicio e terminou a tela de Manter Eventos e arrumou alguns bugs da tela Manter Viagem. As duas telas funcionam de forma quase identica, para saber como funcionam, olhe o log anterior (10/01/2025 <14:41>). Planos: arrumar EVENTOS para que faca mais sentido, apenas mudando os nomes de algumas tabelas.

* 15/01/2025 <21:59> - Joaquim deu inicio e finalizou as telas de "deletar cliente_viagem" e "deletar cliente_evento". As duas telas coletam os dados das tabelas Cliente_Viagem e Cliente_Evento respectivamente, e mostram pro administrador, nessas telas, pode somente deletar essa associacao cliente viagem ou evento. Usam DAOS novas e classes novas para guardar o conteudo dessas tabelas, utilizando StringProperty para atualizar simultaneamente as alteracoes feitas pelo administrador na TableView via ObservableList. Planos: arrumar EVENTOS para que faca mais sentido, apenas mudando os nomes de algumas tabelas.

* 20/01/2025 <23:30> - Daniel deu inicio a implementação da Tela "TelaClientePrincipal". Criou o model de "Anotacoes.java" e o DAO de "AnotacoesDAO.java", finalizou ambos. Começou a programar os botões do controller de "NotasControllers".

* 21/01/2025 <23:00> - Daniel finalizou os botões do controller de "NotasControllers". Iniciou a implementação do Banco de Dados para salvar as anotações que o cliente colocava em "NotasText". No momento só conseguiu fazer até a parte de salvar as anotações, pois observou um problema que quando reiniciava a aplicação, as anotações sumiam para a tela do cliente, mas continuavam no banco de dados.

* 23/01/2025 <01:00> - Daniel conseguiu corrigir o problema das anotações sumirem, criou uma lógica para que cada ID de Anotações fosse destinada para um cliente (que no caso seria o cpf do cliente), anteriormente a cada anotação salva ele criava um ID diferente. Finalizou a parte de Anotações e configurou os botões das telas de "TelaViagensDisponiveis.fxml" e "TelaEventosDisponiveis.fxml". 

* 25/01/2025 <18:10> - Daniel criou as telas "TelaViagemMarcadas.fxml" e "TelaEventosMarcados.fxml". Configurou e arrumou todos os erros das telas "TelaViagensDisponiveis.fxml" e "TelaEventosDisponiveis", conseguiu fazer ambas as tabelas mostrarem o que deveriam mostrar, que para a da tela de viagens disponíveis, mostre as viagens disponíveis, e para a de eventos disponíveis, mostrar os eventos disponíveis. Configurou as telas "TelaAgendarEvento.fxml" e "TelaAgendarViagem.fxml", agora o cliente consegue agendar os eventos e viagens disponíveis. Agora só faltará fazer as telas de "TelaViagensMarcadas.fxml" e de "TelaEventosMarcados.fxml" funcionarem, que assim que feitas, a parte do cliente estará finalizada.

* 29/01/2025 <10:32> - Daniel Terminou as telas de "TelaViagensMarcadas.fxml" e "TelaEventosMarcados.fxml", cujos dados sao coletados pelo banco de dados, e exibem views ja existentes no banco de dados. Daniel também adicionou alertas para o usuario caso haja algum erro em algum dado que ele escreveu na tela que esta presente. Joaquim arrumou alguns bugs no codigo e garantiu que o codigo estava funcionando. (OBS: o codigo foi commitado de uma conta aleatoria logada no pc da escola, quem fez o commit foi o Daniel)

* 02/02/2025 <21:40> - Joaquim fez a tela de "Manter Cliente" na parte da administracao completamente funcional (basicamente um copia e cola de manter evento ou viagem, ou seja as funcionalidades programadas sao praticamente iguais) e fez um icone de login na tela principal do cliente, permitindo ele deslogar se desejar, e caso seja um administrador, permita a ele voltar a tela de menu do administrador. Daniel deu inicio ao termino dos ultimos diagramas restantes do TCA.

* 02/02/2025 <22:45> - Joaquim fez algumas correcoes de bugs.

* 03/02/2025 <22:08> - Joaquim finalizou o design, corrigiu uns erros na aparencia e texto e poliu o restante do visual do projeto.

* 03/02/2025 <23:07> - Joaquim adicionou novas colunas a "Deletar Cliente_Viagem" e "Deletar Cliente_Eventos", para melhorar o visual dessas telas e dar mais nocao ao usuario do que esta deletando.Daniel esta produzindo e finalizando os ultimos diagramas do projeto. Provavelmente, o ultimo commit grande na main.