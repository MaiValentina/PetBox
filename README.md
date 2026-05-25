# PetBox — Plataforma de microservicios

Sistema full-stack basado en **10 microservicios Spring Boot** independientes, cada uno con su propia base de datos MySQL. Cubre el dominio completo de una tienda especializada en mascotas: usuarios, mascotas, suscripciones a cajas de productos personalizadas, inventario, pedidos, pagos, envíos, notificaciones y reseñas.

## Equipo

| Integrante | Correo |
|---|---|
| Mai Olivares | mv.olivares@duocuc.cl |
| Leon Artiaga | le.artiaga@duocuc.cl |
| Joaquin Olivos | jo.olivos@duocuc.cl |

## Microservicios

| Servicio | Puerto | Base de datos |
|---|---|---|
| suscripciones | 8081 | petbox_suscripciones |
| mascotas | 8082 | petbox_mascotas |
| envios | 8083 | petbox_envios |
| inventario | 8084 | petbox_inventario |
| notificaciones | 8085 | petbox_notificaciones |
| pagos | 8086 | petbox_pagos |
| pedidos | 8087 | petbox_pedidos |
| personalizacion | 8088 | petbox_personalizacion |
| resenas | 8089 | petbox_resenas |
| usuarios | 8090 | petbox_usuarios |

## Requisitos

- Java 21
- Maven 3.9+
- MySQL en localhost:3306 (Laragon, usuario `root` sin contraseña)

## Cómo ejecutar un microservicio

```bash
cd mascotas
./mvnw spring-boot:run
```
