#include <DHT.h>
#include <ESP8266WiFi.h>
#define DHTPIN 5

DHT dht(DHTPIN, DHT11);

const char *ssid =  "ForaBolsonaro";
const char *pass =  "minha-senha";

const char* server = "api.thingspeak.com";
String apiKey = "minha-Wkey";

String thingtweetAPIKey = "minha-Rkey";

WiFiClient client;

void setup() {
  Serial.begin(115200);
  delay(10);  
  Serial.println("Connecting to ");
  Serial.println(ssid);  
  WiFi.begin(ssid, pass);  
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
  dht.begin();
  pinMode(13, OUTPUT);
  pinMode(12, OUTPUT);
}

void loop() {
  float h = dht.readHumidity();
  float t = dht.readTemperature();

  if (isnan(h) || isnan(t))
  {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  if (t > 28.0f) {
    ligaAmarelo();
    updateTwitterStatus("Tweet enviado pela NodeMCU quando a temperatura for superior a 28°C");
  }else{
    ligaBranco();
    String msg = "Tweet enviado pela NodeMCU: temperatura = ";
    String msg2 = " ,umidade = ";
    updateTwitterStatus(msg + t + msg2 + h);
  }

  if (client.connect(server, 80))  //   "184.106.153.149" or api.thingspeak.com
  {    String postStr = apiKey;
    postStr += "&field1=";
    postStr += String(t);
    postStr += "&field2=";
    postStr += String(h);
    postStr += "\r\n\r\n";    client.print("POST /update HTTP/1.1\n");
    client.print("Host: api.thingspeak.com\n");
    client.print("Connection: close\n");
    client.print("X-THINGSPEAKAPIKEY: " + apiKey + "\n");
    client.print("Content-Type: application/x-www-form-urlencoded\n");
    client.print("Content-Length: ");
    client.print(postStr.length());
    client.print("\n\n");
    client.print(postStr);    
    Serial.print("Temperature: ");
    Serial.print(t);
    Serial.print(" degrees Celcius, Humidity: ");
    Serial.print(h);
    Serial.println("%. Send to Thingspeak.");
  }
  client.stop();  
  Serial.println("Waiting...");

  delay(5000);
}

void updateTwitterStatus(String tsData)
{
  if (client.connect(server, 80))
  {
    // Create HTTP POST Data
    tsData = "api_key=" + thingtweetAPIKey + "&status=" + tsData;    
    client.print("POST /apps/thingtweet/1/statuses/update HTTP/1.1\n");
    client.print("Host: api.thingspeak.com\n");
    client.print("Connection: close\n");
    client.print("Content-Type: application/x-www-form-urlencoded\n");
    client.print("Content-Length: ");
    client.print(tsData.length());
    client.print("\n\n");    client.print(tsData);
  }
}

void ligaBranco() {
  digitalWrite(13, LOW);
  digitalWrite(12, HIGH);
  delay(500); 
  digitalWrite(12, LOW); 
  delay(500);
}
void ligaAmarelo() {
  digitalWrite(12, LOW);
  digitalWrite(13, HIGH);
  delay(500); 
  digitalWrite(13, LOW); 
  delay(500);
}
