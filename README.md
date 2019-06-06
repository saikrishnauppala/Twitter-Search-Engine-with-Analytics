# Twitter-Search-Engine-with-Analytics
* This project is a Complete Information Retrieval system along with data analytics and Visualization on twitter data.<br>
* The project is built from scratch using solr-6.6.5,Twitter API,java servlets,jsp,html,bootstrap,JavaScript,google chart API.<br>
* Data is crawled from twitter for over 4 weeks. All the data is Cleaned and indexed in SOLR.<br>
* In SOLR all the schema configurations are made and Okapi BM25 Model is chosen as ranking Model.<br>
* SOLR is deployed on AWS<br>
* A web application is implemented using servlets to interact with SOLR. <br>
* Whenever a query is queried from web application relavent results are fetched ordered by rank from SOLR. Moreover data analyticson those results is visualized in the web application using google charts JS. 
* Analytics for data is provided based on sentiment,trend, city, language.
