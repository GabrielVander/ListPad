# Especificação de projeto final

### Projeto integrado das disciplinas de Programação de Dispositivos Android 1 (PA1) e Banco de Dados para Computação Móvel (BDM)

# Vído narrado
https://youtu.be/MZ9MuBmXFzU

# Projeto ListPad

O projeto consiste no desenvolvimento de um aplicativo simples de lista em lista com uso de
persistência. O aplicativo permite criar, apagar e gerenciar listas (no plural) de itens quaisquer (
tarefas, materiais escolares, compras etc.), que chamaremos aqui de lista de itens. A tela principal
do aplicativo deve mostrar as listas de itens cadastradas, ter opções para que sejam adicionadas
novas, para visualizar detalhes (isto é, os itens que fazem parte) e para apagar listas de itens
existentes. Cada lista de itens deve possuir um nome (que deve ser único), uma breve descrição, uma
flag (para determinar se a lista é urgente ou não) e uma categoria que pode ser tarefas, compras,
compromissos e geral. Não é obrigatório, mas seria apreciado se o usuário também pudesse cadastrar e
remover outras categorias e que essas categorias novas pudessem ser usadas para categorizar as
listas de itens.

Cada lista de itens é composta de zero ou mais itens. Cada item é composto de uma descrição (que
deve ser única) e uma flag (que diz se aquele item foi cumprido ou não). Os itens de uma lista devem
ser visualizados na tela de detalhes da lista de itens a qual pertencem. Nessa tela o usuário pode
adicionar, excluir e checar itens (alterar estado da flag) como cumpridos ou não.

Lista de itens e itens devem estar relacionados e manter observância aos conceitos de
desenvolvimento de um banco de dados. A persistência deve ser implementada usando algum dos métodos
desenvolvidos na disciplina de BDM.

A entrega final consiste na entrega do projeto e de um vídeo mostrando o aplicativo em uso, narrado
e demonstrando as principais funcionalidades (no máximo 5 minutos). A entrega do deverá ser feita
por compartilhamento de repositório no GitHub mantendo nome de projeto e repositório como ListPad. O
vídeo deverá ser entregue junto do repositório. A data de entrega final será divulgada em momento
oportuno pelos professores responsáveis pela disciplina.

Dica: Para entender o que é um aplicativo de lista em lista, veja a lista de conversas que você
possui na tela principal do seu Whatsapp. Perceba que é mostrada uma lista de todas as conversas que
você possui nos últimos tempos. Essas conversas aparecem na lista com a identificação do contato com
quem você trocou mensagens. Clicando em qualquer uma das conversas você tem acesso a lista de
mensagens trocadas entre você e o contato. Isso é um aplicativo que possui uma lista (de conversas)
que leva a outra lista (de mensagens).
