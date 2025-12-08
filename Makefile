.PHONY: run clean package

run:
	@clj -M -m core

clean:
	@clj -T:build clean

package:
	@clj -T:build package
