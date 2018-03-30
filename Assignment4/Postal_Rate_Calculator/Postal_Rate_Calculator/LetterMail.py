
class LetterMail:
	
	@staticmethod
	def calculatePostage(length, width, weight):

		postage = 0.0

		if length < 140 or length > 380:
			print("length should be from 140mm to 380mm")
			raise ValueError("length should be from 140mm to 380mm")

		if width< 90 or width> 270:
			print("length should be from 140mm to 380mm")
			raise ValueError("length should be from 140mm to 380mm")
		
		if weight < 3.0 or weight > 500.0:
			print("length should be from 140mm to 380mm")
			raise ValueError("length should be from 140mm to 380mm")

		if length >= 140.0 and length <= 245.0 and width >= 90.0 and width <= 156.0:
			if weight >= 3.0 and weight <= 30.0:
				postage = 0.49
			elif weight > 30.0 and weight <= 50.0:
				postage = 0.80
			elif weight > 50.0 and weight <= 100.0:
				postage = 0.98
			elif weight > 100.0 and weight <= 500.0:
				postage = 2.40

		if length > 245.0 and length <= 380.0 and width >= 90.0 and width <= 156.0:
			if weight >= 3.0 and weight <= 100.0:
				postage = 0.98
			elif weight > 100 and weight <= 500.0:
				postage = 2.40

		if length > 140.0 and length <= 380.0 and width >= 156.0 and width <= 270:
			if weight >= 3.0 and weight <= 100.0:
				postage = 0.98
			elif weight > 100 and weight <= 500.0:
				postage = 2.40


		return postage
		