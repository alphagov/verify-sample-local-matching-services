FROM python:2.7.13

env FLASK_APP local_matching_service.py

COPY ./requirements.txt /app/requirements.txt
WORKDIR /app

RUN pip install -r requirements.txt

COPY . /app

EXPOSE 5000

CMD ["flask", "run", "--host", "0.0.0.0"]
