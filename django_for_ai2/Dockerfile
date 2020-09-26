FROM python:3.7

RUN mkdir django

WORKDIR /django

COPY . .

RUN pip install -r requirements.txt

RUN python manage.py makemigrations

RUN python manage.py migrate


EXPOSE 8002

ENTRYPOINT python manage.py runserver
