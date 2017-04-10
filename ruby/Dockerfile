FROM ruby:2.3.1

ENV APPPATH /app

ENV PATH $APPPATH/bin:$PATH

RUN mkdir -p $APPPATH
COPY . $APPPATH
WORKDIR $APPPATH

RUN bundle check || bundle install

EXPOSE 50139

CMD [ "bundle", "exec", "./lms.rb" ]