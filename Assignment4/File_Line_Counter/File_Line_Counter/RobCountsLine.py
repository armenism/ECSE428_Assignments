
class RobCountsLine(object):
    def count_line_number(self):
        return sum(1 for line in open('file.txt'))


if __name__ == '__main__':

    try:
        total_lines = RobCountsLine().count_line_number()
        print("Total Number Lines " + str(total_lines))
    except FileNotFoundError as e:
        print("FileNotFoundException Occurred" + str(e))
    except Exception as e:
        print("IOException Occurred" + str(e))
