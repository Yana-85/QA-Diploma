# ***Дипломный проект по курсу «Тестировщик ПО»***
## ***О проекте***

Проект представляет собой комплексное автоматизированное тестирование сервиса по покупке туров через интернет-банк. Купить тур можно с помощью двух способов:

- оплата с помощью дебетовой карты
- покупка в кредит

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

- сервису платежей (Payment Gate)
- кредитному сервису (Credit Gate)

Приложение в собственной СУБД сохраняет информацию о том, каким способом был совершён платёж и успешно ли он был совершён.

## ***Документация***
- [План автоматизации тестирования](https://github.com/Yana-85/QA-Diploma/blob/master/documentation/Plan.md)
- [Отчет по итогам тестирования](https://github.com/Yana-85/QA-Diploma/blob/master/documentation/Report.md)
- [Отчет по итогам автоматизации](https://github.com/Yana-85/QA-Diploma/blob/master/documentation/Summary.md)

## ***Перед началом работы***
***1.*** Необходимо склонировать [репозиторий](https://github.com/Yana-85/QA-Diploma) с помощью команды `git clone` или воспользоваться VCS Git, встроенной в IntelliJ IDEA.

***2.*** Установить [Docker](https://www.docker.com/), инструкция по установке по [ссылке](https://github.com/netology-code/aqa-homeworks/blob/master/docker/installation.md).

***3.*** Открыть проект в Intellij IDEA, инструкция по установке по [ссылке](https://github.com/netology-code/javaqa-homeworks/blob/master/intro/idea.md).
## ***Запуск***
***1. Запускаем docker-контейнер с СУБД MySQL и PostgreSQL, а также Node.js командой в терминале:***
```
docker-compose up -d
```
***2. Запускаем SUT***

Для этого открываем новую вкладку в терминале IDEA и вводим следующую команду:

- для СУБД ***MySQL***:

```
java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
```
- для СУБД ***PostgreSQL***:
```
java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
```

***3. Запускаем авто-тесты***

Для этого открываем еще одну вкладку в терминале и вводим следующую команду:

- для СУБД ***MySQL***:

```
gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
```

- для СУБД ***PostgreSQL***:

```
gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
```
***4. Генерируем отчет Allure по итогам тестирования***

Для запуска и просмотра отчета выполняем две команды по очереди:
```
gradlew allureReport
```
```
gradlew allureServe
```
Отчет открывается после прохождения тестов автоматически в браузере по умолчанию.
После просмотра отчета останавливаем действие allureServe комбинацией клавиш **Ctrl + C**.
## ***Завершение работы Sut***
Для завершения работы SUT, необходимо в терминале, где был запущен SUT, ввести команду:
```
 Ctrl+C
```
## ***Остановка и удаление контейнеров***
***1.*** Для остановки контейнеров в терминале IDEA вводим следующую команду:
```
docker-compose stop
``` 
***2.*** Для удаления контейнеров в терминале вводим команду:
```
docker-compose down
``` 