Vertex AI Android App

Descrição do Projeto

Este projeto consiste em um aplicativo Android que se comunica com o serviço Vertex AI da Google Cloud utilizando requisições HTTP.
O fluxo principal do aplicativo inclui:
- Preparar um "pedido" de texto para ser enviado à IA.
- Autenticar a requisição via OAuth2.
- Enviar o texto a um modelo de linguagem hospedado na nuvem.
- Receber e processar a resposta gerada pelo Vertex AI.

Estrutura do Projeto

O projeto foi desenvolvido com base em um template padrão do Android Studio (Empty Views Activity), adicionando algumas dependências específicas:

Dependências Principais

Biblioteca	Função
- com.squareup.retrofit2:retrofit	Facilita a criação de chamadas HTTP através de interfaces Java/Kotlin.
- com.squareup.retrofit2:converter-gson	Integra Retrofit com Gson, permitindo converter JSON em objetos Java automaticamente.
- org.jetbrains.kotlinx:kotlinx-coroutines-android	Suporte a operações assíncronas sem bloqueio de interface (UI).
- com.squareup.okhttp3:logging-interceptor	(Opcional) Registra o conteúdo das requisições e respostas HTTP para fins de debug.
  
Comunicação com o Vertex AI
Interface VertexApiService

A interface VertexApiService define o comportamento de chamadas HTTP via Retrofit. Ela permite:
- Especificar dinamicamente o Project ID, Location ID e Model ID.
- Enviar o corpo da requisição (em formato JSON).
- Definir o cabeçalho de autenticação OAuth2.

Exemplos de parâmetros:
- Project ID: projetoTeste1234
- Location ID: us-central1
- Model ID: text-bison@001 ou gemini-1.5-pro-preview-0409

Modelos de Dados:
- PredictRequest: representa o conteúdo enviado para o modelo.
- PredictResponse: representa o conteúdo recebido como resposta.

Autenticação com OAuth2
- Para garantir a segurança das requisições, o app utiliza tokens OAuth2 gerados localmente.

Procedimento para geração de token:
Instalar o Google Cloud SDK.

Executar o login na conta Google Cloud:
- gcloud auth application-default login

Baixar o arquivo JSON de credenciais do projeto.

Ativar as APIs necessárias no Console do Google Cloud:
- Vertex AI API
- Generative Language API

Ativar a Service Account com o arquivo JSON:
- gcloud auth activate-service-account --key-file="CAMINHO/para/arquivo.json"

Gerar o token de acesso:
- gcloud auth application-default print-access-token
  
O token gerado deve ser usado no cabeçalho HTTP como:
- Authorization: Bearer <token>

Observações Relevantes
- A base URL https://us-central1-aiplatform.googleapis.com/ não responde diretamente no navegador, pois exige rotas específicas para os modelos.
- O Location ID refere-se ao datacenter onde o serviço da IA está hospedado, e não à localização geográfica do usuário.
- É necessário ativar o Billing no projeto Google Cloud para liberar o uso completo de modelos como Gemini ou Text-Bison.

