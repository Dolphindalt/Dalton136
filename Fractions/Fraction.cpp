#include <cstdio>
#include <cassert>
#include <cmath>

class Fraction {
public:
	Fraction(int x, int y);
	Fraction();
	Fraction operator=(Fraction f);
	bool operator==(Fraction f);
	void print() const;
	Fraction reduce();
	Fraction reciprocal();
	Fraction operator+(Fraction f);
	Fraction operator-(Fraction f);
	Fraction operator*(Fraction f);
	Fraction operator/(Fraction f);
	int getX() const;
	int getY() const;
private:
	int gcd();
	int _x, _y; // x is the numerator, y is the denominator
};

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
	return Fraction(_x - f.getX(), _y - f.getY());	
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
