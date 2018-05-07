package com.ncontracts.katas.stringcalculator

class StringCalculator {

	int add(String numbers) {
		if (!numbers?.trim()) return 0

		def delimiters = ',\n'
		def delimitedNumberString = numbers

		if (numbers.startsWith('//')) {
			delimiters += numbers.substring(2, numbers.indexOf('\n'))
			delimitedNumberString = numbers.substring(numbers.indexOf('\n') + 1)
		}

		def integers = delimitedNumberString.tokenize(delimiters)*.toInteger()
		integers.removeAll { it > 1000 }

		def negatives = integers.findAll { it < 0 }
		if (negatives) throw new IllegalArgumentException("negatives not allowed: ${negatives.join(', ')}")

		return integers.sum()
	}
}