.PHONY: run clean package

run:
	@clj -M:run

clean:
	@clj -T:build clean

package:
	@clj -T:build package
