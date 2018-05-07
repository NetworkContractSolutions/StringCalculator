using System;
using System.Collections.Generic;
using System.Linq;

namespace StringCalculator
{
    public class Calculator
    {

        public int Add(string numbers)
        {
            if (string.IsNullOrWhiteSpace(numbers))
                return 0;

            var delimitors = new List<char>{',', '\n'};

            if (numbers.StartsWith("//"))
            {
                delimitors.Add(numbers[2]);
                numbers = numbers.Substring(4);
            }

            var ints = numbers.Split(delimitors.ToArray()).Select(int.Parse).ToList();

            var negatives = ints.Where(x => x < 0).ToList();
            if (negatives.Any())
            {
                throw new Exception($"negatives not allowed:{string.Join(",", negatives)}");
            }

            return ints.Sum();

        }
    }
}
