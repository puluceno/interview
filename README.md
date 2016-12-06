Pelo fato de ser um "requerimento" usar JavaEE, o sistema foi configurado para utilizar WildFly 10. As configuraçes de
banco de dados e CORS estão feitas no próprio servidor.

A versão do java utilizada foi 1.8_111;
O banco de dados utilizado foi o MySQL 5.7.16;
O servidor utilizado foi o WildFly 10 (provê todas as dependencias utilizadas no projeto);

Por motivos de integridade dos dados, não existe a opção de "deletar" uma tarefa. A mesma é marcada como removida,
porém não removida do banco de dados.

Caso queira rodar no seu Wildfly local, o arquivo standalone.xml está disponibizado na pasta Resources, juntamente com o 
diagrama do banco de dados (que possibilita geração do mesmo através do MySQL Workbench).
