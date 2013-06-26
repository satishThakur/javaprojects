require 'rubygems'
require 'antwrap'

options = {:ant_home=>"D:/apache-ant-1.7.1", :name=>"nanobench", :logger=> Logger.new(STDOUT)}

@ant = Antwrap::AntProject.new(options)
@ant.path(:id => "src.path"){ |ant|
	ant.pathelement(:location => "src/main/java")
	ant.pathelement(:location => "src/main/resources")
}
@ant.path(:id => "target.path"){ |ant|
	ant.pathelement(:location => "taget/classes")
}
@ant.path(:id => "lib.path"){ |ant|
	ant.pathelement(:location => "taget/classes")
}

def compile
	@ant.javac(:srcdir => "test", :destdir => "taget/classes") do |ant|
		ant.classpath(:refid => "lib.path")
	end
end

def help
	puts "Usage: jruby #{$0} [target]"
	puts <<-EOF
targets:
	compile, comple the java file
	EOF
end

if ARGV.empty?
	help
else
	eval ARGV[0]
end