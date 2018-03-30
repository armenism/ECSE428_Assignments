def enum(**kwargs):
    return type('Enum', (), kwargs)


class GlobalDataCargo:
    """
    For simplicity and to show as an example the methods are done static
    Would have been a nicer code implementation to use it as an object with the arguments passed in the constructor
    """
    __CARGO_LABEL = "C"

    Product = enum(P1='P1', P2='P2', P3='P3', P4='P4', P5='P5', P6='P6', P7='P7')

    @staticmethod
    def get_global_data_cargo_form(cargo1, cargo2, cargo3, cargo4):
        """
        Calculates the result based on the business logic of the provided parameters
        :param cargo1:
        :param cargo2:
        :param cargo3:
        :param cargo4:
        :return:
        """

        result = [GlobalDataCargo.Product.P1]

        if cargo1 and cargo2:
            result.append(GlobalDataCargo.Product.P2)
        else:
            result.append(GlobalDataCargo.Product.P3)

        result.append(GlobalDataCargo.Product.P4)

        if cargo3:
            result.append(GlobalDataCargo.Product.P5)

        result.append(GlobalDataCargo.Product.P6)

        if cargo4:
            result.append(GlobalDataCargo.Product.P7)

        output = ""

        for string in result:
            output += string

        return output

    @staticmethod
    def get_global_data_cargo_headers(cargos):
        """
        Returns the header with the cargo(s) legend
        :param cargos:
        :return: String with the description of
        """

        if cargos is None:
            return ""

        header = ""

        for index in range(len(cargos)):
            header += GlobalDataCargo.__CARGO_LABEL + str(index) + "\t"

        return header

    @staticmethod
    def get_global_data_cargo_input(cargos):
        """
        Return the input as String
        :param cargos:
        :return:
        """
        if cargos is None:
            return ""

        cargo_input = ""

        for cargo in cargos:
            cargo_input += str(cargo) + "\t"

        return cargo_input
