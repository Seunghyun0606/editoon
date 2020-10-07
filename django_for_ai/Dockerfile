FROM python:3.7

RUN mkdir django

WORKDIR /django

COPY . .

RUN apt update

RUN apt install -y libgl1-mesa-glx

RUN pip install -r requirements.txt

RUN python manage.py makemigrations

RUN python manage.py migrate


EXPOSE 8002

ENTRYPOINT gunicorn --bind 0.0.0.0:8002 django_for_ai.wsgi:application

#ENTRYPOINT python manage.py runserver 0.0.0.0:8002
~
