.PHONY: run check style clean package run-package

run:
	@clj -M:run

check:
	@clj-kondo --lint source

style:
	@cljfmt check source

clean:
	@clj -T:build clean

package:
	@clj -T:build package

run-package:
	@java -jar target/application-*.jar
