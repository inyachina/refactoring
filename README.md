# Refactoring

- Студент: Инячина Диана
- Группа: P34111

### Описание

Выбранное приложение - **Курсовая работа по ИСБД**

Проект основан на романе Айзека Азимова «Конец вечности»,который описывает существующую вне времени и пространства
могущественную организацию Вечность, способную осуществлять путешествия во времени. Вечность может перемещаться в любое
время не раньше 27-ого века (в момент, когда она была основана) и не находящиеся в промежутке между 70000 и 150000
веком (сверхлюди блокирует это время). Организация совершает торговлю между временами и изменяет реальность во избежание
угроз.

**Профессии:**

-	Наблюдатели – собирают данные из необходимой реальности
-	Статистики – делают статистику на основе данных для данной реальности
-	Социологи – строят психологическую характеристику общества на основе статистики
-	Вычислители – рассчитывают изменения необходимые для предотвращения события (МНВ) и их побочные эффекты
-	Техники – исполняют изменения
-	Работники – выполняют настройку оборудования, доставляют товары

**Дополнительные сведения:**

-	МНВ (минимальное необходимое воздействие) – вычисляемое решение, которое необходимо выполнить, чтобы предотвратить последствия в будущем
-	МОР (максимальная ожидаемая реакция) – воздействия, которые последуют после применения МНВ
-	Расчет судьбы - влияние изменения во времени на судьбу определённой личности
-	Времянин – человек, живущий во времени.


**Бизнес-процессы:**

- Изменение реальности - вычисление и исполнение МНВ, чтобы предотвратить произошедшее событие. Прежде, чем оно исполняется, заказчик получает МОР и соглашается на изменение
- Расчет судьбы человека
- Импорт/ экспорт товаров из разных эпох в разные (Времяни могут размещать объявлении о продажи товаров, покупать их, организация также может размещать объявлении о продажи товаров)
- Предоставление списка данных, на основе которых сотрудник может выполнить задание

**Сценарии использования:**

Пользователь проходит регистрацию и авторизуется. После этого ему доступно одно из действий:

1. Торговля:
   - Продажа. Пользователь может выставить товар на продажу, при этом указав время, из которого он его продает
   - Покупка. Пользователь может добавить товары разного времени в корзину в нужном количестве, после чего оформить заказ.

2. Изменение реальности. Пользователь указывает время и событие, которое произошло и которое он хочет предотвратить. Ему предоставляется МОР. После ознакомления он с ней соглашается и реальность меняется или отказывается и изменение не происходит.

3. Расчет судьбы. Пользователь указывает данные о времянине, чью судьбу он хочет рассчитать. После этого он получает информацию о рассчитанной судьбе. Пользователи, вошедшие под ролью сотрудников, могут выполнять следующие действия:
   1. Отправлять исследованные данные на обработку другим сотрудникам.
   2. Видеть список данных, которые им необходимо обработать или выполнить.
   3. отправить МОР пользователю. В случае одобрения пользователем, данные о МНВ передаются техникам.
   4. Отправить рассчитанную судьбу пользователю

Наблюдатели получают данные от пользователей о времени, где им необходимо произвести наблюдения.\
Статистики получают данные от наблюдателей.\
Социологи получают данные от статистиков.\
Вычислители получают данные от социологов.\
Техники получают данные от вычислителей.\
Работники могут получить данные от любых пользователей.

Модераторы могут:
-	Добавлять товары на продажу
-	Удалять товары с продажи

### Этапы разработки 
1. Первый этап
   - Исправить REST API:
     - Backend:
       - Изменить типы и url запросов согласно принципам REST
       - Добавить валидацию для данных, передаваемых в http запросах
     - Frontend
       - Согласовать запросы с исправленным backend

2. Второй этап
   - Backend
     - Типизировать возможные ошибки, расширить базу ошибок, прописать для каждой статус и сообщение клиенту  
     - Убрать try/catch блоки из контроллеров и сервисов
     - Добавить глобальный ExceptionHandler 
   - Frontend
     - Добавить отображение серверных ошибок

3. Третий этап
   - Cleanup кода
     - Делегирование задач контроллеров сервисам
     - Рефакторинг сервисов и репозиториев
     - Добавление необходимых транзакций
   - Frontend
     - Улучшение UI/UX
### Результаты рефакторинга:
#### Проверка работоспособности:
[Видео-результат каждого этапа](https://drive.google.com/drive/folders/1vx1eUsP5rjejvwXedh6QCpD080Mq0YkZ?usp=sharing)
#### Сохранение версионности:
![image](https://user-images.githubusercontent.com/56327155/209475352-dcee8a29-64fc-4862-9774-ad4a8e67074f.png)
