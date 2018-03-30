import unittest
from LetterMail import LetterMail

class Test_LetterMail(unittest.TestCase):

    def testRateCalc001(self):
        # length out of range too low
        self.assertRaises(ValueError, LetterMail.calculatePostage, 90, 100, 30)

    def testRateCalc002(self):
        # length out of range too high
        self.assertRaises(ValueError, LetterMail.calculatePostage, 400, 100, 30)
        
    def testRateCalc003(self):
        # width out of range too low
        self.assertRaises(ValueError, LetterMail.calculatePostage, 140, 80, 30)
        
    def testRateCalc004(self):
        # width out of range too high
        self.assertRaises(ValueError, LetterMail.calculatePostage, 140, 300, 30)
        
    def testRateCalc005(self):
        # weight out of range too low
        self.assertRaises(ValueError, LetterMail.calculatePostage, 140, 100, 1.0)
            
    def testRateCalc006(self):
        # weight out of range too high
        self.assertRaises(ValueError, LetterMail.calculatePostage, 140, 100, 600.0)

    def testRateCalc007(self):
        # valid business rule R1
        # 140 <= length <= 245
        # 90 <= width <= 156
        # 3 <= weight <= 30
        expected_rate = 0.49

        self.assertEqual(expected_rate, LetterMail.calculatePostage(200, 100, 10))

    def testRateCalc008(self):
        # valid business rule R2
        # 140 <= length <= 245
        # 90 <= width <= 156
        # 30 <= weight <= 50
        expected_rate = 0.80

        self.assertEqual(expected_rate, LetterMail.calculatePostage(200, 100, 40))

    def testRateCalc009(self):
        # valid business rule R3
        # 140 <= length <= 245
        # 90 <= width <= 156
        # 50 <= weight <= 100
        expected_rate = 0.98

        self.assertEqual(expected_rate, LetterMail.calculatePostage(200, 100, 75))

    def testRateCalc010(self):
        # valid business rule R4
        # 140 <= length <= 245
        # 90 <= width <= 156
        # 100 <= weight <= 500
        expected_rate = 2.40

        self.assertEqual(expected_rate, LetterMail.calculatePostage(200, 100, 250))

    def testRateCalc011(self):
        # valid business rule R5
        # 245 <= length <= 380
        # 90 <= width <= 156
        # 3 <= weight <= 100
        expected_rate = 0.98

        self.assertEqual(expected_rate, LetterMail.calculatePostage(300, 100, 50))

    def testRateCalc012(self):
        # valid business rule R6
        #  245 <= length <= 380
        # 90 <= width <= 156
        # 30 <= weight <= 500
        expected_rate = 2.40

        self.assertEqual(expected_rate, LetterMail.calculatePostage(300, 100, 200))

    def testRateCalc013(self):
        # valid business rule R7
        # 140 <= length <= 380
        # 156 <= width <= 270
        # 50 <= weight <= 100
        expected_rate = 0.98

        self.assertEqual(expected_rate, LetterMail.calculatePostage(300, 200, 50))

    def testRateCalc014(self):
        # valid business rule R8
        # 140 <= length <= 380
        # 156 <= width <= 270
        # 100 <= weight <= 500
        expected_rate = 2.40

        self.assertEqual(expected_rate, LetterMail.calculatePostage(380, 270, 500))


if __name__ == '__main__':
    unittest.main()
