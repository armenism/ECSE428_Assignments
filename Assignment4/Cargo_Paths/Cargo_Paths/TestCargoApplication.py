import unittest

from GlobalDataCargo import GlobalDataCargo


class TestCargoApplication(unittest.TestCase):
    def test_truth_table_all_true(self):
        expected = "P1P2P4P5P6P7"
        actual = GlobalDataCargo.get_global_data_cargo_form(True, True, True, True)

        self.assertEqual(expected, actual)

    def test_truth_table_C4_false(self):
        expected = "P1P2P4P5P6"
        actual = GlobalDataCargo.get_global_data_cargo_form(True, True, True, False)

        self.assertEqual(expected, actual)

    def test_truth_table_C3_false(self):
        expected = "P1P2P4P6P7"
        actual = GlobalDataCargo.get_global_data_cargo_form(True, True, False, True)

        self.assertEqual(expected, actual)

    def test_truth_table_C3C4_false(self):
        expected = "P1P2P4P6"
        actual = GlobalDataCargo.get_global_data_cargo_form(True, True, False, False)

        self.assertEqual(expected, actual)

    def test_truth_table_C2_false(self):
        expected = "P1P3P4P5P6P7"
        actual = GlobalDataCargo.get_global_data_cargo_form(True, False, True, True)

        self.assertEqual(expected, actual)

    def test_truth_table_C2C4_false(self):
        expected = "P1P3P4P5P6"
        actual = GlobalDataCargo.get_global_data_cargo_form(True, False, True, False)

        self.assertEqual(expected, actual)

    def test_truth_table_C2C3_false(self):
        expected = "P1P3P4P6P7"
        actual = GlobalDataCargo.get_global_data_cargo_form(True, False, False, True)

        self.assertEqual(expected, actual)

    def test_truth_table_C1_false(self):
        expected = "P1P3P4P5P6P7"
        actual = GlobalDataCargo.get_global_data_cargo_form(False, True, True, True)

        self.assertEqual(expected, actual)

    def test_truth_table_C1C4_false(self):
        expected = "P1P3P4P5P6"
        actual = GlobalDataCargo.get_global_data_cargo_form(False, True, True, False)

        self.assertEqual(expected, actual)

    def test_truth_table_C1C3_false(self):
        expected = "P1P3P4P6P7"
        actual = GlobalDataCargo.get_global_data_cargo_form(False, True, False, True)

        self.assertEqual(expected, actual)

    def test_truth_table_C1C3C4_false(self):
        expected = "P1P3P4P6"
        actual = GlobalDataCargo.get_global_data_cargo_form(False, True, False, False)

        self.assertEqual(expected, actual)

    def test_truth_table_C1C2_false(self):
        expected = "P1P3P4P5P6P7"
        actual = GlobalDataCargo.get_global_data_cargo_form(False, False, True, True)

        self.assertEqual(expected, actual)

    def test_truth_table_C1C2C4_false(self):
        expected = "P1P3P4P5P6"
        actual = GlobalDataCargo.get_global_data_cargo_form(False, False, True, False)

        self.assertEqual(expected, actual)

    def test_truth_table_C1C2C3_false(self):
        expected = "P1P3P4P6P7"
        actual = GlobalDataCargo.get_global_data_cargo_form(False, False, False, True)

        self.assertEqual(expected, actual)

    def test_truth_table_C1C2C3C4_false(self):
        expected = "P1P3P4P6"
        actual = GlobalDataCargo.get_global_data_cargo_form(False, False, False, False)

        self.assertEqual(expected, actual)

if __name__ == '__main__':
    unittest.main()
