package com.ncontracts.katas.stringcalculator

import spock.lang.Specification

class StringCalculatorSpec extends Specification {

	def calculator = new StringCalculator()

	def "passing empty string to add() method returns zero"() {
		expect:
		calculator.add('') == 0
	}

	def "passing single integer string to add() method returns that number as integer"() {
		expect:
		calculator.add(input) == sum

		where:
		input  << ["5", "7", "1"]
		sum    << [5, 7, 1]
	}

	def "passing two-integer comma-delimited string to add() method returns the sum of those two numbers"() {
		expect:
		calculator.add(input) == sum

		where:
		input  << ["5,8", "7,4", "1,3"]
		sum    << [13,    11,     4]
	}

	def "passing n-integer comma-delimited string to add() method returns the sum of those n numbers"() {
		expect:
		calculator.add(input) == sum

		where:
		input  << ["5,8,9", "7,4,8,3", "1,3,5,7,9"]
		sum    << [22,      22,        25]
	}

	def "add() method can use commas or newlines as delimiters"() {
		expect:
		calculator.add(input) == sum

		where:
		input  << ["5\n8\n9", "7,4\n8,3", "1,3\n5,7\n9"]
		sum    << [22,      22,        25]
	}

	def "add() method can handle custom and standard delimiters"() {
		expect:
		calculator.add(input) == sum

		where:
		input  << ["//;\n5;8\n9", "//;\n7;4\n8,3", "//;\n1;3\n5,7\n9"]
		sum    << [22,      22,        25]
	}

	def "add() method throws exception when negatives are passed"() {
		when:
		def sum = calculator.add("-5,-8\n-9")

		then:
		IllegalArgumentException e = thrown()
		e.message == "negatives not allowed: -5, -8, -9"
	}

	def "add() method ignores numbers > 1000"() {
		expect:
		calculator.add(input) == sum

		where:
		input  << ["//;\n5;8\n9;500000", "//;\n7;4\n8,3;1000", "//;\n1;3\n5,7\n9;1001"]
		sum    << [22,                   1022,                 25]
	}

	def "add() method handles repeating delimiters"() {
		expect:
		calculator.add(input) == sum

		where:
		input  << ["//;\n5;;;;;8\n\n9;500000", "//;\n\n7;4\n8,,,,,,3;;;1000", "//;\n1;;;;3\n5,,7\n\n\n\n\n9;1001"]
		sum    << [22,                         1022,                          25]
	}

	def "add() method handles multiple custom delimiters"() {
		expect:
		calculator.add(input) == sum

		where:
		input  << ["//;|*\n5;|;*,8\n\n9;|,500000", "//;^(\n\n7(;4\n^8,^,^,^3;(\n1000", "//;~\n1;~;\n3\n~5,;7\n~\n~\n9;1001"]
		sum    << [22,                             1022,                               25]
	}

	def "add() method handles multi-character custom delimiters"() {
		expect:
		calculator.add(input) == sum

		where:
		input  << ["//->\n5->->8\n->,9->500000", "//:-)\n7:-)4\n:-),:-)\n8\n3:-):-):-)1000", "//?:\n1?:,\n3?:?:5,7?:?:?:9,\n?:1001"]
		sum    << [22,                           1022,                                       25]
	}
}
