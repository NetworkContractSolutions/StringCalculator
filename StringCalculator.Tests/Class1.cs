using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework;

namespace StringCalculator.Tests
{
    public class StringCalculatorTests
    {
        [Test]
        public void EmptyString_ReturnZero()
        {
            var calculator = new Calculator();
            Assert.AreEqual(0, calculator.Add("")); 
        }

        [Test]
        [TestCase("1",1)]
        [TestCase("2",2)]
        [TestCase("5",5)]
        public void PassingInOneNumber_ReturnOne(string numbers, int expectedResult)
        {
            var calculator = new Calculator();
            Assert.AreEqual(expectedResult, calculator.Add(numbers)); 
        }

        [Test]
        [TestCase("1,2",3)]
        [TestCase("2,2",4)]
        [TestCase("3,2",5)]
        public void PassingInTwoNumbers_ReturnSum(string numbers, int expectedResult)
        {
            var calculator = new Calculator();
            Assert.AreEqual(expectedResult, calculator.Add(numbers)); 
        }

        [Test]
        [TestCase("1,2,3",6)]
        [TestCase("3,3,3,3", 12)]
        [TestCase("1,2,3,4,5", 15)]
        public void PassingInMultipleNumbers_ReturnSum(string numbers, int expectedResult)
        {
            var calculator = new Calculator();
            Assert.AreEqual(expectedResult, calculator.Add(numbers)); 
        }

        [Test]
        [TestCase("1\n2,3",6)]
        [TestCase("3,3,3,3", 12)]
        [TestCase("1,2,3\n4,5", 15)]
        public void PassingInMultipleNumbersWithNewLine_ReturnSum(string numbers, int expectedResult)
        {
            var calculator = new Calculator();
            Assert.AreEqual(expectedResult, calculator.Add(numbers)); 
        }

        [Test]
        [TestCase("//;\n1;2",3)]
        [TestCase("//$\n1$3",4)]
        [TestCase("//$\n1$3,5",9)]
        [TestCase("//$\n1\n3",4)]
        [TestCase("//@\n1@5",6)]
        public void PassingInCustomDelimitor_ReturnSum(string numbers, int expectedResult)
        {
            var calculator = new Calculator();
            Assert.AreEqual(expectedResult, calculator.Add(numbers)); 
        }

        [Test]
        [TestCase("-5,1","negatives not allowed:-5")]
        [TestCase("-5,1,-8,-9","negatives not allowed:-5,-8,-9")]
        [TestCase("//@\n1@5@-2","negatives not allowed:-2")]
        [TestCase("//-\n1--5","Input string was not in a correct format.")]
        public void NegativeNumbers_Throw(string numbers, string expectedResult)
        {
            var calculator = new Calculator();
            try
            {
                calculator.Add(numbers);
                Assert.Fail();
            }
            catch (Exception exception)
            {
                Assert.AreEqual(expectedResult, exception.Message);
            }
        }

    }
}
