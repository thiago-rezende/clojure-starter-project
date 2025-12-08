.PHONY: run clean package run-package

run:
	@clj -M:run

clean:
	@clj -T:build clean

package:
	@clj -T:build package

run-package:
	@java -jar target/application-*.jar
