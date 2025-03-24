# IDHM dos Municípios Brasileiros

Cenario:

- Um gestor público solicitou a uma consultoria um levantamento de diversas informações sobre os municípios brasileiros. O objetivo é estudar características e propor políticas adequadas nas áreas de Educação, Saúde e aumento de Renda. Para auxiliar nesse estudo, utilize a base de dados `cidades_do_brasil.xlsx` e responda às seguintes perguntas:

- Panorama Geral: O primeiro indicador a ser analisado é o `Índice de Desenvolvimento Humano Municipal (IDHM)`, que é composto por três dimensões: longevidade, educação e renda. O índice varia de 0 a 1, sendo que quanto mais próximo de 1, maior o desenvolvimento humano.

Questões:

a) Quartil Inferior:

- Qual o valor que separa os 25% dos municípios com os menores IDHM? Utilize o conceito de quartis.

b) Concentração Geográfica:

- Considerando os municípios com menor IDHM, existe uma concentração em algum estado ou distrito? Utilize o conceito de Pareto para fundamentar sua resposta.

c) Classificação ONU:

- A ONU classifica o IDH em quatro faixas:
  - Baixo: < 0,550
  - Médio: 0,550 - 0,699
  - Alto: 0,700 - 0,799
  - Muito Alto: >= 0,800
  - Pergunta: Com base nessa classificação, analise a situação atual do Brasil e de cada região.

Educação Infantil:

d) Boxplot por Região:

- Construa um boxplot da variável IDHM_Educacao por região (NO, NE, SE, S e CO). Comente as semelhanças e diferenças entre os gráficos.

e) Municípios com Mais Crianças:

- Na região com a menor média de IDHM_Educacao, identifique os municípios que estão acima do 3º quartil nacional em relação à quantidade de crianças com idade entre 1 e 4 anos (variável IBGE_1-4).

f) Empresas de Educação:

- Identifique os municípios que estão no 1º quartil nacional em relação à quantidade de empresas do setor educacional (variável COMP_P). Dessa forma, vamos priorizar municípios com poucas empresas de educação.

g) Prioridade de Investimento:

- Considerando as análises anteriores, proponha uma ordem de priorização para investimento na educação dessa região.

Fonte: `https://www.kaggle.com/crisparada/brazilian-cities/downloads/brazilian-cities.zip/6`
