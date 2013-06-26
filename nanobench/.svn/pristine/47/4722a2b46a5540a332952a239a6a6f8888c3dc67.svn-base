import 'com.alisoft.nb.Nano'
import 'java.lang.Thread'

class NanobenchTest < Test::Unit::TestCase
	def test_nano_bench
		task = Thread.new {a = [1,2,3,4]; 100.times {a.to_java :string}}
		Nano.bench.measure(task)
	end
end