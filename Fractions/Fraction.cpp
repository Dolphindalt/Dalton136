#include <cstdio>
#include <cassert>
#include <cmath>

/// This class represents a fraction, it can do fraction things.
class Fraction {
public:
	/// Constructs a fraction.
	/// param! x The numerator
	/// param! y The denominator
	Fraction(int x, int y);
	/// Constructs a fraction with a numerator and denominator of one.
	Fraction();
	/// Hard copy implementation of the assignment operator.
	Fraction operator=(Fraction f);
	/// Value checking implementation of the equality operator.
	bool operator==(Fraction f);
	/// Prints out the fraction.
	void print() const;
	/// Reduces a fraction to the lowest terms.
	Fraction reduce();
	/// Flips the values of the numerator and denominator 
	Fraction reciprocal();
	/// Operator overloading for addition of fractions
	Fraction operator+(Fraction f);
	/// Operator overloading for subtraction of fractions
	Fraction operator-(Fraction f);
	/// Operator overloading for multiplication of fractions
	Fraction operator*(Fraction f);
	/// Operator overloading for division of fractions
	Fraction operator/(Fraction f);
	/// Gets the numerator of the fraction
	int getX() const;
	/// Gets the denominator of the fraction
	int getY() const;
private:
	int gcd();
	int _x, _y; // x is the numerator, y is the denominator
};

/// The main function is a test suite, testing and asserting various functions.
int main(int argc, char *argv[])
{
	// operator = test
	Fraction a(50, 30);
	Fraction b(100, 200);
	a = b;
	assert(a.getX() == b.getX() && a.getY() == b.getY());
	// operator == test
	Fraction c(1, 1);
	Fraction d;
	assert(d == c);
	// reduce test
	Fraction e(100, 10);
	e.reduce();
	assert(e.getX() == 10 && e.getY() == 1);
	Fraction f(1, 2);
	f.reduce();
	assert(f.getX() == 1 && f.getY() == 2);
	// reciprocal test
	Fraction g(66, 55);
	g.reciprocal();
	assert(g.getX() == 55 && g.getY() == 66);	
	// operator +
	Fraction h(1, 3);
	Fraction i(1, 2);
	Fraction j = (h + i);
	assert(j.getX() == 5 && j.getY() == 1);
	// operator -
	Fraction k(2, 5);
	Fraction kk(2, 4);
	Fraction kuk = (k - kk);
	assert(kuk.getX() == -2 && kuk.getY() == 1);	
	// operator *
	Fraction l(10, 5);
	Fraction m(2, 4);
	Fraction n = (l * m);
	assert(n.getX() == 20 && n.getY() == 20);
	// operator /
	Fraction o(1, 2);
	Fraction p(2, 1);
	Fraction q = ( o / p);
	assert(q.getX() == 4 && q.getY() == 1);	
	printf("All tests have passed. If you do not know what this means, please inspect the main function.\n");
	return 0;
}

Fraction::Fraction(int x, int y) : _x(x), _y(y) {}

Fraction::Fraction() : _x(1), _y(1) {}

Fraction Fraction::operator=(Fraction f)
{	
	_x = f.getX();	
	_y = f.getY();
	return *this;	
}

bool Fraction::operator==(Fraction f)
{
	Fraction a = *this;
	Fraction b = f;
	a.reduce();
	b.reduce();
	return (a.getX() == b.getX() && a.getY() == b.getY()); // These might need to be reduced
}

void Fraction::print() const
{
	printf("%d/%d\n", _x, _y);	
}

Fraction Fraction::reduce()
{
	int gcd_guy = gcd();
	_x /= gcd_guy;
	_y /= gcd_guy;
	return *this;	
}

Fraction Fraction::reciprocal()
{
	int temp = _y;
	_y = _x;
	_x = temp;
	return *this;
}

Fraction Fraction::operator+(Fraction f)
{
	Fraction common_mult(std::abs(_y) * std::abs(f.getY()), 1);
	Fraction t1 = *this, t2 = f;
	t1 = (t1 * common_mult).reduce();
	t2 = (t2 * common_mult).reduce();
	return Fraction(t1.getX() + t2.getX(), t1.getY());	
}

Fraction Fraction::operator-(Fraction f)
{
	Fraction common_mult(std::abs(_y) * std::abs(f.getY()), 1);
	Fraction t1 = *this, t2 = f;
	t1 = (t1 * common_mult).reduce();
	t2 = (t2 * common_mult).reduce();
	return Fraction(t1.getX() - t2.getX(), t1.getY());	
}

Fraction Fraction::operator*(Fraction f)
{
	return Fraction(_x * f.getX(), _y * f.getY());
}

Fraction Fraction::operator/(Fraction f)
{
	return Fraction(_y * f.getX(), _x * f.getY());
}

int Fraction::gcd()
{
	int tmp, n, m;
	n = std::abs(_x);
	m = std::abs(_y);
	while(n > 0)
	{
		tmp = n;
		n = m % n;
		m = tmp;
	}
	return m;
}

int Fraction::getX() const
{
	return _x;
}

int Fraction::getY() const
{
	return _y;
}
