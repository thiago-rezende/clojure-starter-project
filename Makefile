.PHONY: run lint format clean package run-package

run:
	@clj -M:run

lint:
	@clj-kondo --lint source

format:
	@cljfmt check source

clean:
	@clj -T:build clean

package:
	@clj -T:build package

run-package:
	@java -jar target/application-*.jar
