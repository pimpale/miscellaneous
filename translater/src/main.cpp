#include <iostream>
#include <string>
#include <algorithm>
std::string thiccinator(std::string s)
{
	std::string alphbegin   = "abcdefghijklmnopqrstuvwxyz";
	std::string alphreplace = "卂乃匚刀乇下厶卄工丁长乚从𠘨口尸㔿尺丂丅凵リ山乂丫乙";
	for(unsigned int i = 0; i < alphbegin.length(); i++)
	{
		std::replace( s.begin(), s.end(), alphbegin.at(i), alphreplace.at(i));
	}
	return s;
}


int main()
{
	std::string input;
	std::cin >> input;

	std::cout << thiccinator(input) << std::endl;

}


