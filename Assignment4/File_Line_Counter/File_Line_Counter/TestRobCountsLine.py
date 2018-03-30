import unittest

from mockito import mock, spy, when, unstub
from RobCountsLine import RobCountsLine


class TestRobCountsLine(unittest.TestCase):
    __DATE = "2018-03-20"

    def test_confirm_line_count(self):
        print("Test confirm_line_count " + self.__DATE)

        line = RobCountsLine().count_line_number()
        self.assertEqual(7, line)

    def test_confirm_mock_of_specific_value(self):
        print("Test confirm_mock_of_specific_value " + self.__DATE)

        # create an instance of the mock of the class RobCountsLine
        rob_counts_line = spy(RobCountsLine())

        rcl = 6847180

        # define the mocking rule
        when(rob_counts_line).count_line_number().thenReturn(rcl)

        # try to run the method countLineNumber which should be mocked bt rcl
        line = rob_counts_line.count_line_number()

        self.assertEqual(rcl, line)

        # return to the original unMocked method
        unstub(rob_counts_line)

        # try to run the method countLineNumber which should be back to original
        line = rob_counts_line.count_line_number()

        self.assertEqual(7, line)

    def test_confirm_mock_of_call_big_line_number(self):
        print("Test confirm_mock_of_call_big_line_number " + self.__DATE)

        rob_counts_line = mock(RobCountsLine())

        when(rob_counts_line).count_line_number().thenReturn(self.big_line_file())

        line = rob_counts_line.count_line_number()

        self.assertEqual(self.big_line_file(), line)

    def test_mock_of_file_not_found(self):
        print("Test confirm_mock_of_file_not_found " + self.__DATE)

        rob_counts_line = mock(RobCountsLine())

        # when count_line_number method is run it will throw a FileNotFoundException
        when(rob_counts_line).count_line_number().thenRaise(FileNotFoundError)

        try:
            total_lines = rob_counts_line.count_line_number()
            self.fail("Should have tripped on FileNotFoundException")
        except FileNotFoundError as e:
            print("FileNotFoundException Occurred" + str(e))
        except Exception as e:
            self.fail("Should not be a IOException")

    def test_mock_of_IOException(self):
        print("Test confirm_mock_of_IOException " + self.__DATE)

        rob_counts_line = mock(RobCountsLine())
        
        # when count_line_number method is run it will throw a IOException
        when(rob_counts_line).count_line_number().thenRaise(Exception)

        try:
            total_lines = rob_counts_line.count_line_number()
            self.fail("Should have tripped on IOException")
        except FileNotFoundError as e:
            self.fail("Should not be a FileNotFoundException")
        except Exception as e:
            print("IOException Occurred" + str(e))

    def test_confirm_mock_of_call_small_line_number(self):
        print("Test confirm_mock_of_call_small_line_number " + self.__DATE)

        rob_counts_line = mock(RobCountsLine())

        when(rob_counts_line).count_line_number().thenReturn(self.small_line_file())

        line = rob_counts_line.count_line_number()

        self.assertEqual(self.small_line_file(), line)

    def test_confirm_mock_of_call_negative_line_number(self):
        print("Test confirm_mock_of_call_negative_line_number " + self.__DATE)

        rob_counts_line = mock(RobCountsLine())

        when(rob_counts_line).count_line_number().thenReturn(self.negative_line_file())

        line = rob_counts_line.count_line_number()

        self.assertEqual(self.negative_line_file(), line)

    def big_line_file(self):
        rcl = 3346719
        print("Generates large file line count " + self.__DATE)
        return rcl

    def small_line_file(self):
        rcl = 0
        print("Generates small file line count " + self.__DATE)
        return rcl

    def negative_line_file(self):
        rcl = -1234
        print("Generates negative file line count " + self.__DATE)
        return rcl

if __name__ == '__main__':
    unittest.main()
