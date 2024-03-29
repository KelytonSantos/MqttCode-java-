package com.example;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Main {
    public static void main(String[] args) {
        
        String broker = "tcp://192.168.1.50:1883";
        String clientId = "";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
            mqttClient.connect();
            
            // Subscreva aos tópicos relevantes aqui
            mqttClient.subscribe("teste/l");
            
            mqttClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {
                }

                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    String message = new String(mqttMessage.getPayload());
                    System.out.println("Mensagem recebida: " + message);
                    
                    //inserirNoBancoDeDados(message);
                }

                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                }
            });
            
            mqttClient.close();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    
    /*private static void inserirNoBancoDeDados(String message) {
    }*/

}
//----------------
/*public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
    String message = new String(mqttMessage.getPayload());
    System.out.println("Mensagem recebida: " + message);
    
    if (topic.equals("sensores/temperatura")) {
        // Lógica para lidar com dados de temperatura
        processarDadosTemperatura(message);
    } else if (topic.equals("sensores/umidade")) {
        // Lógica para lidar com dados de umidade
        processarDadosUmidade(message);
    } else {
        // Lógica para outros tópicos
        processarOutrosDados(message);
    }
}*/