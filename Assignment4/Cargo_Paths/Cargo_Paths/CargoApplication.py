import sys

from GlobalDataCargo import GlobalDataCargo

__EXPECTED_ARGUMENTS = 4
gdc = GlobalDataCargo()

if __name__ == '__main__':

    args = sys.argv[1:]

    if (args is None) or (len(args) is not __EXPECTED_ARGUMENTS):
        raise ValueError("This application only accepts 4 arguments")

    arguments = []

    for arg in args:
        arguments.append(str(arg.lower()) == 'true')

    print(gdc.get_global_data_cargo_headers(arguments))
    print(gdc.get_global_data_cargo_input(arguments))

    result = gdc.get_global_data_cargo_form(arguments[0], arguments[1], arguments[2], arguments[3])
    print("Result: " + result)

